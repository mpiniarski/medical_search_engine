import { BehaviorSubject } from 'rxjs/BehaviorSubject';

export function watchArticles(store, body: Object): BehaviorSubject<any> {
    return store.getArticles(body);
}

export function watchArticlesWithDecisionSupport(store, body: Object): BehaviorSubject<any> {
    return store.getArticlesWithDecisionSupport(body);
}