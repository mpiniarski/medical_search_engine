import { Component, OnInit, Input } from '@angular/core';

import { SearchBoxComponent } from '../search-box/search-box.component';

@Component({
    selector: 'app-article',
    templateUrl: './article.component.html',
    styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {

    private starred: boolean[] = [];
    private expand: boolean[] = [];
    positiveArticles: Object[] = [];
    negativeArticles: Object[] = [];

    @Input() articles;

    constructor() { }

    ngOnInit() { }

    private expandArticle(index): void {
        this.expand[index] === true ? this.expand[index] = false : this.expand[index] = true;
    }

    private ratePositive(index): void {
        if (this.starred[index] !== true) {
            if (this.starred[index] === false) {
                const articleIndex = this.negativeArticles.indexOf(this.articles[index].measureMap);
                this.negativeArticles.splice(articleIndex, 1);
            }
            this.positiveArticles.push(this.articles[index].measureMap);
            this.starred[index] = true;
        }
    }

    private rateNegative(index): void {
        if (this.starred[index] !== false) {
            if (this.starred[index] === true) {
                const articleIndex = this.positiveArticles.indexOf(this.articles[index].measureMap);
                this.positiveArticles.splice(articleIndex, 1);
            }
            this.negativeArticles.push(this.articles[index].measureMap);
            this.starred[index] = false;
        }
    }

    private rateNeutral(index): void {
        if (this.starred[index] !== undefined) {
            if (this.starred[index] === false) {
                const articleIndex = this.negativeArticles.indexOf(this.articles[index].measureMap);
                this.negativeArticles.splice(articleIndex, 1);
            } else if (this.starred[index] === true) {
                const articleIndex = this.positiveArticles.indexOf(this.articles[index].measureMap);
                this.positiveArticles.splice(articleIndex, 1);
            }
            this.starred[index] = undefined;
        }
    }

    public getPositiveArticles(): Object[] {
        return this.positiveArticles;
    }

    public getNegativeArticles(): Object[] {
        return this.negativeArticles;
    }

    public resetArticlesSettings(): void {
        this.positiveArticles = [];
        this.negativeArticles = [];
        this.starred = [];
        this.expand = [];
    }

    public resetArticles(): void {
        this.articles = [];
    }
}
