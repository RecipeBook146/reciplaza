import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterUser'
})
export class FilterUserPipe implements PipeTransform {

  transform(items: any[], searchText: string, fieldName: string): any[] {
    if (!items) { return []; }
    if (!searchText) { return []; }
    searchText = searchText.toLowerCase();
    return items.filter(item => {
      if (item && item[fieldName]) {
        return item[fieldName].toLowerCase() === searchText;
      }
      return false;
    });
  }

}
