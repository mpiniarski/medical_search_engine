import { Component, OnInit, Input } from '@angular/core';

import { SearchBoxComponent } from '../search-box/search-box.component';

@Component({
    selector: 'app-article',
    templateUrl: './article.component.html',
    styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {

    private states: boolean[] = [];
    private expand: boolean[] = [];
    private pageNumber: number = 0;
    private pageArticles: Object[] = [];
    positiveArticles: Map<number, Object> = new Map();
    negativeArticles: Map<number, Object> = new Map();

    @Input() articles;
    @Input() pageLength;

    constructor() { }

    ngOnInit() { }

    private isSinglePage(): boolean {
        return this.getLastPageNumber() === this.pageNumber && this.pageNumber === 0;
    }

    private getLastPageNumber(): number {
        return Math.ceil(this.articles.length / this.pageLength) - 1;
    }

    private nextPage(): void {
        const lastPage = this.getLastPageNumber();
        if (lastPage > this.pageNumber) {
            this.pageNumber++;
            this.changePageArticles(this.pageNumber);
            this.setPageArticleSettings(this.pageNumber);
        }
    }

    private previousPage(): void {
        if (this.pageNumber > 0) {
            this.pageNumber--;
            this.changePageArticles(this.pageNumber);
            this.setPageArticleSettings(this.pageNumber);
        }
    }

    private setPageArticleSettings(page: number): void {
        const startIndex = page * this.pageLength;
        const endIndex = (page + 1) * this.pageLength;
        for (var i = startIndex; i < endIndex; i++) {
            if (this.getPositiveArticles().has(this.articles[i].article.id)) {
                this.setArticleDisplaySettings(i, true);
            } else if (this.getNegativeArticles().has(this.articles[i].article.id)) {
                this.setArticleDisplaySettings(i, false);
            }
        }
    }

    private isExpanded(index: number): boolean {
        const globalIndex = this.getGlobalIndex(index);
        return this.expand[globalIndex];
    }

    private isStatePositive(index: number): boolean {
        const globalIndex = this.getGlobalIndex(index);
        return this.states[globalIndex];
    }

    private changePageArticles(pageNumber: number): void {
        this.pageArticles = this.articles.slice(pageNumber * this.pageLength, (pageNumber + 1) * this.pageLength);
    }

    public setPageArticles(articles: Object[]): void {
        this.pageArticles = articles;
    }

    public setPageNumber(number: number): void {
        this.pageNumber = number;
    }

    public setArticleDisplaySettings(index: number, state: boolean): void {
        this.states[index] = state;
    }

    private getGlobalIndex(index): number {
        return this.pageNumber * this.pageLength + index;
    }

    private expandArticle(index): void {
        const globalIndex = this.getGlobalIndex(index);
        this.expand[globalIndex] === true ? this.expand[globalIndex] = false : this.expand[globalIndex] = true;
    }

    private ratePositive(index): void {
        const globalIndex = this.getGlobalIndex(index);
        if (this.states[globalIndex] !== true) {
            if (this.states[globalIndex] === false) {
                this.negativeArticles.delete(this.articles[globalIndex].article.id);
            }
            this.positiveArticles.set(this.articles[globalIndex].article.id, this.articles[globalIndex].measureMap);
            this.states[globalIndex] = true;
        }
    }

    private rateNegative(index): void {
        const globalIndex = this.getGlobalIndex(index);
        if (this.states[globalIndex] !== false) {
            if (this.states[globalIndex] === true) {
                this.positiveArticles.delete(this.articles[globalIndex].article.id);
            }
            this.negativeArticles.set(this.articles[globalIndex].article.id, this.articles[globalIndex].measureMap);
            this.states[globalIndex] = false;
        }
    }

    private rateNeutral(index): void {
        const globalIndex = this.getGlobalIndex(index);
        if (this.states[globalIndex] !== undefined) {
            if (this.states[globalIndex] === false) {
                this.negativeArticles.delete(this.articles[globalIndex].article.id);
            } else if (this.states[globalIndex] === true) {
                this.positiveArticles.delete(this.articles[globalIndex].article.id);
            }
            this.states[globalIndex] = undefined;
        }
    }

    public getPositiveArticles(): Map<number, Object> {
        return this.positiveArticles;
    }

    public getNegativeArticles(): Map<number, Object> {
        return this.negativeArticles;
    }

    public resetArticlesSettings(): void {
        this.positiveArticles.clear();
        this.negativeArticles.clear();
    }

    public resetArticlesDisplaySettings(): void {
        this.states = [];
        this.expand = [];
    }

    public resetArticles(): void {
        this.articles = [];
        this.pageArticles = [];
    }
}
