import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../services/product.service";
import {Product} from "../../model/product";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  dataSource: Product[] = [];
  companyList: string[] = [];
  productList: string[] = [];
  companyFilter: String = '';
  productFilter: String = '';

  constructor(public productService: ProductService) {
  }

  ngOnInit(): void {
    this.loadInventory();
  }

  filterByCompany(): Product[] {
    return this.dataSource.filter(product => product.company === this.companyFilter || this.companyFilter === 'All')
      .filter(product => product.product === this.productFilter || this.productFilter === 'All');
  }

  loadInventory() {
    this.productService.getInventory().subscribe(value => {
      this.dataSource = value.products;

      this.companyList = this.dataSource.map(item => item.company).filter((value, index, self) => self.indexOf(value) === index)
      this.companyFilter = this.companyList[0];

      this.productList = this.dataSource.map(item => item.product).filter((value, index, self) => self.indexOf(value) === index)
      this.productFilter = 'All';
    });
  }

}
