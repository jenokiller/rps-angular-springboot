import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {RpsComponent} from './rps/rps.component';

const routes: Routes = [
  {
    path: 'rps', component: RpsComponent,
  },
  {
    path: '',
    redirectTo: 'rps',
    pathMatch: 'full'
  }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
