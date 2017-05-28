import { Component, OnInit, Input, ViewChild } from '@angular/core';

import { AppStore } from '../appStore/app.store';
import * as watchers from '../appStore/app.store.watchers';

import { SearchBody, SearchBodyWithWeights } from '../interfaces/common.interfaces';

import { TokenComponent } from '../token/token.component';

@Component({
    selector: 'app-search-box',
    templateUrl: './search-box.component.html',
    styleUrls: ['./search-box.component.scss'],
    providers: [AppStore]
})
export class SearchBoxComponent implements OnInit {

    private query: string;
    articles: Object[] = [];
    tokens: string[] = [];

    @Input() currentAlgorithm;
    @ViewChild('tokensComponent') tokensComponent: TokenComponent;

    constructor(
        private appStore: AppStore
    ) { }

    ngOnInit() { }

    private search(): void {
        const tokenWeights = this.getTokenWeights();
        if (Object.keys(tokenWeights).length === 0 && tokenWeights.constructor === Object) {
            this.sendSearch();
        } else {
            this.sendSearchWithWeights();
        }
    }

    private sendSearch(): void {
        const body: SearchBody = {
            query: this.query,
            sortingAlgorithm: this.currentAlgorithm,
        };
        this.watchArticles(body);
    }

    private sendSearchWithWeights(): void {
        const body: SearchBodyWithWeights = {
            query: this.query,
            sortingAlgorithm: this.currentAlgorithm,
            weights: this.getTokenWeights(),
            positiveArticles: [],
            negativeArticles: []
        };
        this.watchArticlesWithWeights(body);
    }

    private watchArticles(body: Object): void {
        watchers.watchArticles(this.appStore, body).subscribe(response => {
            this.articles = response.articles;
            this.tokens = this.getTokens(response.query.measureMap);
        });
    }

    private watchArticlesWithWeights(body: Object): void {
        watchers.watchArticlesWithWeights(this.appStore, body).subscribe(response => {
            this.articles = response.articles;
            this.tokens = this.getTokens(response.query.measureMap);
        });
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
        return this.tokensComponent.getTokensWithWeights();
    }
}
