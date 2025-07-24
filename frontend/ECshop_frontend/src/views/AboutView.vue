<template>
  <div class="about">
    <h1>訂單清單</h1>
    <table  >
      <thead>
      <tr>
        <th>訂單編號</th>
        <th>會員編號</th>
        <th>商品清單</th>
        <th>總價</th>
        <th>付款狀態</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(order, index) in groupedOrders" :key="index">
        <td>{{ order.orderId }}</td>
        <td>{{ order.memberId }}</td>
        <td>{{ order.productSummary }}</td>
        <td>{{ order.totalPrice }}</td>
        <td>{{ order.payStatus === 1 ? '已付款' : '未付款' }}</td>
      </tr>
      </tbody>
    </table>
    <p v-if="groupedOrders.length === 0">載入中或無訂單資料</p>
  </div>
</template>

<script>

import {fetchOrdersByUser} from "@/API";

export default {
  name: 'AboutView',
  data() {
    return {
      groupedOrders: []
    };
  },
  async created() {
    try {

      const response = await   fetchOrdersByUser(localStorage.getItem("userID"))


      const rawOrders = response.data;

      // 根據 orderId 群組
      const grouped = {};
      for (const order of rawOrders) {
        if (!grouped[order.orderId]) {
          grouped[order.orderId] = {
            orderId: order.orderId,
            memberId: order.memberId,
            payStatus: order.payStatus,
            totalPrice: 0,
            items: []
          };
        }
        grouped[order.orderId].totalPrice = order.price;
        grouped[order.orderId].items.push(`${order.productName} * ${order.quantity}`);
      }

      this.groupedOrders = Object.values(grouped).map(order => ({
        ...order,
        productSummary: order.items.join('、')
      }));
    } catch (error) {
      console.error('載入訂單失敗:', error);
    }
  }
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}
th {
  background-color: #f2f2f2;
}
td, th {
  text-align: left;
}
</style>
