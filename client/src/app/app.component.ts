import { Component } from '@angular/core';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    private appName: string = "MedSort";
    private query: string;
    private starred = [false, false];
    private articles = [
        {
            "title": "Super article",
            "abstract": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed hendrerit non velit non condimentum. Duis convallis feugiat gravida. Aenean in luctus turpis. Suspendisse mollis cursus pellentesque. Integer id tortor posuere, ornare diam at, dictum nunc. In lacinia eros vitae augue consectetur aliquam. Sed malesuada erat at purus scelerisque, in sollicitudin orci ullamcorper."
        },
        {
            "title": "Awesome article",
            "abstract": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed hendrerit non velit non condimentum. Duis convallis feugiat gravida. Aenean in luctus turpis. Suspendisse mollis cursus pellentesque. Integer id tortor posuere, ornare diam at, dictum nunc. In lacinia eros vitae augue consectetur aliquam. Sed malesuada erat at purus scelerisque, in sollicitudin orci ullamcorper."
        },
        {
            "title": "The greatest article of all time with the best title in the world",
            "abstract": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed hendrerit non velit non condimentum. Duis convallis feugiat gravida. Aenean in luctus turpis. Suspendisse mollis cursus pellentesque. Integer id tortor posuere, ornare diam at, dictum nunc. In lacinia eros vitae augue consectetur aliquam. Sed malesuada erat at purus scelerisque, in sollicitudin orci ullamcorper."
        }
    ]

    search() {
        console.log(this.query);
    }

    rateArticle(index) {
        this.starred[index] === true ? this.starred[index] = false : this.starred[index] = true;
    }
}