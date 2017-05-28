import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { AppAsyncs } from './app.asyncs';

@Injectable()
export class AppStore {

    constructor(
        private appAsyncs: AppAsyncs
    ) { }

    getArticles(body: Object): Observable<any> {
        return this.appAsyncs.getArticles(body);
    }

    getArticlesWithWeights(body: Object): Observable<any> {
        return this.appAsyncs.getArticlesWithWeights(body);
    }
}
