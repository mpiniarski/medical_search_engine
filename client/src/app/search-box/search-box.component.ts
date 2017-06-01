import { Component, OnInit, Input, ViewChild } from '@angular/core';

import { AppStore } from '../appStore/app.store';
import * as watchers from '../appStore/app.store.watchers';

import { SearchBody, SearchBodyWithDecisionSupport, QueryWithDecisionSupport } from '../interfaces/common.interfaces';

import { TokenComponent } from '../token/token.component';
import { ArticleComponent } from '../article/article.component';

@Component({
    selector: 'app-search-box',
    templateUrl: './search-box.component.html',
    styleUrls: ['./search-box.component.scss'],
    providers: [AppStore]
})
export class SearchBoxComponent implements OnInit {

    private query: string;
    private loading: boolean = false;
    private pageLength: number = 10;
    private emptyArticlesList = false;
    private emptyArticlesListLabel = 'No results for Your query';
    articles: Object[] = [];
    tokens: string[] = [];

    @Input() currentAlgorithm;
    @ViewChild('tokenComponent') tokenComponent: TokenComponent;
    @ViewChild('articleComponent') articleComponent: ArticleComponent;

    constructor(
        private appStore: AppStore
    ) { }

    ngOnInit() { }

    private search(): void {
        const tokenWeights = this.getTokenWeights();
        if (Object.keys(tokenWeights).length === 0 && tokenWeights.constructor === Object) {
            this.sendSearch();
        } else {
            this.sendSearchWithDecisionSupport();
        }
    }

    private sendSearch(): void {
        const body: SearchBody = {
            queryText: this.query,
            sortingAlgorithm: this.currentAlgorithm,
        };
        this.watchArticles(body);
    }

    private sendSearchWithDecisionSupport(): void {
        const query: QueryWithDecisionSupport = {
            queryText: this.query,
            weights: this.getTokenWeights(),
            positiveArticles: this.getPositiveArticles(),
            negativeArticles: this.getNegativeArticles()
        }
        const body: SearchBodyWithDecisionSupport = {
            query: query,
            sortingAlgorithm: this.currentAlgorithm,
        };
        this.watchArticlesWithDecisionSupport(body);
    }

    private watchArticles(body: Object): void {
        this.loading = true;
        this.resetData();
        this.resetSettings();
        watchers.watchArticles(this.appStore, body).subscribe(response => {
            this.articles = response.articles;
            if (!this.articles.length) {
                this.emptyArticlesList = true;
            }
            this.setPageArticles(0);
            this.tokens = this.getTokens(response.query.measureMap);
            this.tokenComponent.initTokensNumbers(this.tokens.length);
            this.loading = false;
        }, error => this.loading = false);
    }

    private watchArticlesWithDecisionSupport(body: Object): void {
        this.loading = true;
        this.resetData();
        watchers.watchArticlesWithDecisionSupport(this.appStore, body).subscribe(response => {
            this.articleComponent.resetArticlesSettings();
            this.articles = response.articles;
            if (!this.articles.length) {
                this.emptyArticlesList = true;
            }
            this.setPageArticles(0);
            this.tokens = this.getTokens(response.query.measureMap);
            this.loading = false;
        }, error => this.loading = false);
    }

    public setPageArticles(page: number): void {
        this.articleComponent.setPageNumber(page);
        const pageArticles = this.articles.slice(page, this.pageLength);
        this.articleComponent.setPageArticles(pageArticles);
    }

    private getTokens(object: Object[]): string[] {
        let tokens: string[] = [];
        for (var key in object) {
            if (object.hasOwnProperty(key)) {
                tokens.push(key);
            }
        }
        return tokens;
    }

    private getTokenWeights(): Object {
        return this.tokenComponent.getTokensWithWeights();
    }

    private getPositiveArticles(): Object[] {
        const iter = this.articleComponent.getPositiveArticles().values();
        const length = this.articleComponent.getPositiveArticles().size;
        let articles: Object[] = [];
        for (var i = 0; i < length; i++) {
            articles.push(iter.next().value);
        }

        return articles;
    }

    private getNegativeArticles(): Object[] {
        const iter = this.articleComponent.getNegativeArticles().values();
        const length = this.articleComponent.getNegativeArticles().size;
        let articles: Object[] = [];
        for (var i = 0; i < length; i++) {
            articles.push(iter.next().value);
        }

        return articles;
    }

    private onKeyup(event): void {
        this.resetData();
        this.resetSettings();
    }

    private resetData(): void {
        this.articleComponent.resetArticles();
        this.tokenComponent.resetTokens();
        this.emptyArticlesList = false;
    }

    private resetSettings(): void {
        this.articleComponent.resetArticlesSettings();
        this.tokenComponent.resetTokensSettings();
    }
}
