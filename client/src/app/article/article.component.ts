import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-article',
    templateUrl: './article.component.html',
    styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {

    private starred = [false, false];
    private expand = [false, false];
    private articles = [
        {
            "title": "Super article",
            "date": "14.11.2014",
            "author": "Stephen King",
            "abstract": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur urna odio, elementum quis tellus in, euismod pellentesque massa. Sed et ultricies felis. Aenean eleifend velit urna, ut laoreet nunc tempor nec. In vitae auctor odio, ut ornare enim. Nullam euismod ante elit, sed fermentum tortor sagittis eget. Aenean sed nunc orci. Donec sagittis nibh in nibh consectetur, at hendrerit ex sagittis. Sed et mauris sed lectus scelerisque tempus. Suspendisse dui purus, accumsan sed ligula id, dignissim convallis ipsum. Nulla in lacus vel nisl molestie luctus. Pellentesque aliquet sapien ac nunc tristique, vulputate accumsan felis eleifend.\n\nMaecenas auctor, elit et laoreet pellentesque, dui erat sagittis arcu, nec lobortis quam urna et magna. Pellentesque quis mauris id ligula consectetur pellentesque ut et mauris. Donec eu tincidunt urna, ut ultrices mi. Proin finibus vitae massa eget elementum. Cras vitae lacus at augue rutrum posuere sed eu augue. Pellentesque bibendum bibendum nibh, eu vestibulum odio auctor eleifend. Nam feugiat magna non est tincidunt blandit."
        },
        {
            "title": "Awesome article",
            "date": "14.11.2014",
            "author": "Stephen King",
            "abstract": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur urna odio, elementum quis tellus in, euismod pellentesque massa. Sed et ultricies felis. Aenean eleifend velit urna, ut laoreet nunc tempor nec. In vitae auctor odio, ut ornare enim. Nullam euismod ante elit, sed fermentum tortor sagittis eget. Aenean sed nunc orci. Donec sagittis nibh in nibh consectetur, at hendrerit ex sagittis. Sed et mauris sed lectus scelerisque tempus. Suspendisse dui purus, accumsan sed ligula id, dignissim convallis ipsum. Nulla in lacus vel nisl molestie luctus. Pellentesque aliquet sapien ac nunc tristique, vulputate accumsan felis eleifend."
        },
        {
            "title": "The greatest article of all time with the best title in the world",
            "date": "14.11.2014",
            "author": "Stephen King",
            "abstract": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur urna odio, elementum quis tellus in, euismod pellentesque massa. Sed et ultricies felis. Aenean eleifend velit urna, ut laoreet nunc tempor nec. In vitae auctor odio, ut ornare enim. Nullam euismod ante elit, sed fermentum tortor sagittis eget. Aenean sed nunc orci. Donec sagittis nibh in nibh consectetur, at hendrerit ex sagittis. Sed et mauris sed lectus scelerisque tempus. Suspendisse dui purus, accumsan sed ligula id, dignissim convallis ipsum. Nulla in lacus vel nisl molestie luctus. Pellentesque aliquet sapien ac nunc tristique, vulputate accumsan felis eleifend."
        }
    ]

    constructor() { }

    ngOnInit() { }

    expandArticle(index) {
        this.expand[index] === true ? this.expand[index] = false : this.expand[index] = true;
    }

    rateArticle(index) {
        this.starred[index] === true ? this.starred[index] = false : this.starred[index] = true;
    }

}
