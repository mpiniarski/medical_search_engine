import { Component, OnInit } from '@angular/core';

import { AppStore } from '../appStore/app.store';
import * as watchers from '../appStore/app.store.watchers';

@Component({
    selector: 'app-search-box',
    templateUrl: './search-box.component.html',
    styleUrls: ['./search-box.component.scss'],
    providers: [AppStore]
})
export class SearchBoxComponent implements OnInit {

    private query: string;
    articles;

    constructor(
        private appStore: AppStore
    ) { }

    ngOnInit() { }

    private search() {
        const body = {
            "query": this.query,
            "sortingAlgorithm": "TF"
        };
        this.watchArticles(body);
    }

    private watchArticles(body: Object): void {
        watchers.watchArticles(this.appStore, body).subscribe(articles => {
            this.articles = articles.articles;
        });
    }
}
