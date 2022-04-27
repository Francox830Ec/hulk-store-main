export class Product {

  company: string;
  identification: string;
  product: string;
  stock: number;

  constructor(company: string, identification: string, product: string, stock: number) {
    this.company = company
    this.identification = identification
    this.product = product
    this.stock = stock
  }
}
