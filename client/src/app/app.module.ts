import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MdlModule } from 'angular2-mdl';
import { MdlSelectModule } from '@angular2-mdl-ext/select';

import { AppComponent } from './app.component';
import { EllipsisPipe } from './ellipsis.pipe';
import { ArticleComponent } from './article/article.component';
import { SearchBoxComponent } from './search-box/search-box.component';
import { TokenComponent } from './token/token.component';

import { AppStore } from './appStore/app.store';
import { AppAsyncs } from './appStore/app.asyncs';
import { AlgorithmDropdownComponent } from './algorithm-dropdown/algorithm-dropdown.component';
import { FloatingPointPipe } from './floating-point.pipe';

@NgModule({
    declarations: [
        AppComponent,
        EllipsisPipe,
        ArticleComponent,
        SearchBoxComponent,
        TokenComponent,
        AlgorithmDropdownComponent,
        FloatingPointPipe
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        MdlModule,
        MdlSelectModule
    ],
    providers: [
        AppStore,
        AppAsyncs
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
