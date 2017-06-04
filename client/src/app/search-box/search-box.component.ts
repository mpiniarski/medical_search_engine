import { Component, OnInit, Input, ViewChild } from '@angular/core';

import { AppStore } from '../appStore/app.store';
import * as watchers from '../appStore/app.store.watchers';

import { SearchBody, SearchBodyWithDecisionSupport, QueryWithDecisionSupport, ArticleObject } from '../interfaces/common.interfaces';

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
    private emptyArticlesList: boolean = false;
    private emptyArticlesListLabel: string = 'No results for Your query';
    private serverError: boolean = false;
    private serverErrorLabel: string = 'Something went wrong. We\'re sorry. Please Try again.';
    private displayResetButton: boolean = false;
    private resetButtonLabel: string = 'Reset support settings';
    articles: ArticleObject[] = [];
    tokens: string[] = [];

    @Input() currentAlgorithm;
    @ViewChild('tokenComponent') tokenComponent: TokenComponent;
    @ViewChild('articleComponent') articleComponent: ArticleComponent;

    constructor(
        private appStore: AppStore
    ) { }

    ngOnInit() { }

    private search(): void {
        if (this.query) {
            const tokenWeights = this.getTokenWeights();
            if (Object.keys(tokenWeights).length === 0 && tokenWeights.constructor === Object) {
                this.sendSearch();
            } else {
                this.sendSearchWithDecisionSupport();
            }
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
        this.serverError = false;
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
            this.displayResetButton = true;
            this.loading = false;
        }, error => {
            if(error.status === 500 || error.status === 504) {
                this.serverError = true;
            }
            this.loading = false
        });
    }

    private watchArticlesWithDecisionSupport(body: Object): void {
        this.loading = true;
        this.serverError = false;
        this.resetData();
        watchers.watchArticlesWithDecisionSupport(this.appStore, body).subscribe(response => {
            this.articleComponent.resetArticlesDisplaySettings();
            this.articles = response.articles;
            if (!this.articles.length) {
                this.emptyArticlesList = true;
            }
            this.setPageArticles(0);
            this.setPageArticleSettings(0);
            this.tokens = this.getTokens(response.query.measureMap);
            this.displayResetButton = true;
            this.loading = false;
        }, error => {
            if(error.status === 500 || error.status === 504) {
                this.serverError = true;
            }
            this.loading = false
        });
    }

    private setPageArticles(page: number): void {
        this.articleComponent.setPageNumber(page);
        const pageArticles = this.articles.slice(page, this.pageLength);
        this.articleComponent.setPageArticles(pageArticles);
    }

    public setPageArticleSettings(page: number): void {
        const startIndex = page * this.pageLength;
        const endIndex = (page + 1) * this.pageLength;
        for (var i = startIndex; i < endIndex; i++) {
            if (this.articleComponent.getPositiveArticles().has(this.articles[i].article.id)) {
                this.articleComponent.setArticleDisplaySettings(i, true);
            } else if (this.articleComponent.getNegativeArticles().has(this.articles[i].article.id)) {
                this.articleComponent.setArticleDisplaySettings(i, false);
            }
        }
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
        this.displayResetButton = false;
        this.serverError = false;
    }

    private resetData(): void {
        this.articleComponent.resetArticles();
        this.tokenComponent.resetTokens();
        this.emptyArticlesList = false;
    }

    private resetSettings(): void {
        this.articleComponent.resetArticlesSettings();
        this.articleComponent.resetArticlesDisplaySettings();
        this.tokenComponent.resetTokensSettings();
    }

    private resetButton(): void {
        this.articleComponent.resetArticlesSettings();
        this.articleComponent.resetArticlesDisplaySettings();
        this.tokenComponent.initTokensNumbers(this.tokens.length);
    }
}
