import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Http,  RequestOptions, URLSearchParams } from '@angular/http';

@Injectable()
export class AppAsyncs {

    articlesUrl: string = 'v1/search/query';
    articlesWithWeightsUrl: string = 'v1/search/term';

    constructor(
        private http: Http
    ) { }

    getArticles(body: Object): Observable<any> {
        return this.http.post(this.articlesUrl, body)
                        .map((res) => res.json());
    }

    getArticlesWithWeights(body: Object): Observable<any> {
        return this.http.post(this.articlesWithWeightsUrl, body)
                        .map((res) => res.json());
    }
}
