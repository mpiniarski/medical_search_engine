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
    private pageNumber: number = 0;
    private pageArticles: Object[] = [];
    positiveArticles: Object[] = [];
    negativeArticles: Object[] = [];

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
        }
    }

    private previousPage(): void {
        if (this.pageNumber > 0) {
            this.pageNumber--;
            this.changePageArticles(this.pageNumber);
        }
    }

    private isExpanded(index: number): boolean {
        const globalIndex = this.getGlobalIndex(index);
        return this.expand[globalIndex];
    }

    private isStarred(index: number): boolean {
        const globalIndex = this.getGlobalIndex(index);
        return this.starred[globalIndex];
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

    private getGlobalIndex(index): number {
        return this.pageNumber * this.pageLength + index;
    }

    private expandArticle(index): void {
        const globalIndex = this.getGlobalIndex(index);
        this.expand[globalIndex] === true ? this.expand[globalIndex] = false : this.expand[globalIndex] = true;
    }

    private ratePositive(index): void {
        const globalIndex = this.getGlobalIndex(index);
        if (this.starred[globalIndex] !== true) {
            if (this.starred[globalIndex] === false) {
                const articleIndex = this.negativeArticles.indexOf(this.articles[globalIndex].measureMap);
                this.negativeArticles.splice(articleIndex, 1);
            }
            this.positiveArticles.push(this.articles[globalIndex].measureMap);
            this.starred[globalIndex] = true;
        }
    }

    private rateNegative(index): void {
        const globalIndex = this.getGlobalIndex(index);
        if (this.starred[globalIndex] !== false) {
            if (this.starred[globalIndex] === true) {
                const articleIndex = this.positiveArticles.indexOf(this.articles[globalIndex].measureMap);
                this.positiveArticles.splice(articleIndex, 1);
            }
            this.negativeArticles.push(this.articles[globalIndex].measureMap);
            this.starred[globalIndex] = false;
        }
    }

    private rateNeutral(index): void {
        const globalIndex = this.getGlobalIndex(index);
        if (this.starred[globalIndex] !== undefined) {
            if (this.starred[globalIndex] === false) {
                const articleIndex = this.negativeArticles.indexOf(this.articles[globalIndex].measureMap);
                this.negativeArticles.splice(articleIndex, 1);
            } else if (this.starred[globalIndex] === true) {
                const articleIndex = this.positiveArticles.indexOf(this.articles[globalIndex].measureMap);
                this.positiveArticles.splice(articleIndex, 1);
            }
            this.starred[globalIndex] = undefined;
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
        this.pageArticles = [];
    }
}
