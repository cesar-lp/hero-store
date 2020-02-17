import { trigger, state, style, transition, animate } from '@angular/animations';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { PaymentOrderService } from 'src/app/services/payment-order.service';
import { PaymentOrder } from 'src/app/models/payment-order';
import { DataSource } from '@angular/cdk/table';
import { Observable, of } from 'rxjs';

@Component({
  selector: 'product-order-list',
  templateUrl: './product-order-list.component.html',
  styleUrls: ['./product-order-list.component.css'],
  providers: [PaymentOrderService],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0', visibility: 'hidden' })),
      state('expanded', style({ height: '*', visibility: 'visible' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ProductOrderListComponent {

  paymentOrders;
  displayedColumns = ["id", "date", "productsAmount", "total"];
  isExpansionDetailRow = (i: number, row: Object) => row.hasOwnProperty('detailRow');
  expandedElement: any;

  constructor(
    private paymentOrderService: PaymentOrderService,
    private router: Router) { }

  ngOnInit() {
    this.paymentOrderService
      .getAllPaymentOrders()
      .subscribe(results => {
        this.paymentOrders = new PaymentOrdersDatasource(results);
      });
  }

  createProductOrder() {
    this.router.navigate(['/product-orders/new']);
  }
}

export class PaymentOrdersDatasource extends DataSource<any> {

  constructor(private paymentOrders: PaymentOrder[]) {
    super();
  }

  connect(): Observable<PaymentOrder[]> {
    const rows = [];
    this.paymentOrders.forEach(order => rows.push(order, { detailRow: true, element: order.productOrders[0] }));
    console.log(rows);
    return of(rows);
  }

  disconnect() { }
}