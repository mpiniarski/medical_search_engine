import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Http,  RequestOptions, URLSearchParams } from '@angular/http';

@Injectable()
export class AppAsyncs {

    articlesUrl: string = 'v1/search/query';

    constructor(
        private http: Http
    ) { }

    getArticles(body: Object): Observable<any> {
        return this.http.post(this.articlesUrl, body)
                        .map((res) => res.json());
    }
}
