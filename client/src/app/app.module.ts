import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MdlModule } from 'angular2-mdl';

import { AppComponent } from './app.component';
import { EllipsisPipe } from './ellipsis.pipe';
import { ArticleComponent } from './article/article.component';
import { SearchBoxComponent } from './search-box/search-box.component';
import { TokenComponent } from './token/token.component';

@NgModule({
    declarations: [
        AppComponent,
        EllipsisPipe,
        ArticleComponent,
        SearchBoxComponent,
        TokenComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        MdlModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
