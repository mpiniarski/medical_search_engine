import { Component, OnInit, Input } from '@angular/core';

import { AppStore } from '../appStore/app.store';
import * as watchers from '../appStore/app.store.watchers';

import { SearchBody } from '../interfaces/common.interfaces';

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

    constructor(
        private appStore: AppStore
    ) { }

    ngOnInit() { 
    }

    private search() {
        const body: SearchBody = {
            query: this.query,
            sortingAlgorithm: this.currentAlgorithm
        };
        this.watchArticles(body);
    }

    private watchArticles(body: Object): void {
        watchers.watchArticles(this.appStore, body).subscribe(response => {
            this.articles = response.articles;
            this.tokens = this.getTokens(response.query.measureMap);
        });
    }

    private getTokens(object: Object[]) {
        let tokens: string[] = [];
        for (var key in object) {
            if (object.hasOwnProperty(key)) {
                tokens.push(key);
            }
        }
        return tokens;
    }
}
