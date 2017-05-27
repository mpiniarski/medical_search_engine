import { Component, OnInit, Input } from '@angular/core';

@Component({
    selector: 'app-article',
    templateUrl: './article.component.html',
    styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {

    private starred = [false, false];
    private expand = [false, false];

    @Input() articles;

    constructor() { }

    ngOnInit() { }

    expandArticle(index) {
        this.expand[index] === true ? this.expand[index] = false : this.expand[index] = true;
    }

    rateArticle(index) {
        this.starred[index] === true ? this.starred[index] = false : this.starred[index] = true;
    }

}
