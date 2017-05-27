import { BehaviorSubject } from 'rxjs/BehaviorSubject';

export function watchArticles(store, body: Object): BehaviorSubject<any> {
    return store.getArticles(body);
}
