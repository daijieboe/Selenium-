package daijie.admin;

/*参数说明：
 * orderNo：订单号
 * objectStatus：目标订单状态，包括1（在线支付待签收）、2（货到付款扣款完成）、3（签收成功）
 * orderOrigin：订单来源，包括sit和pre
 * @Author DaiJie
 * @Date 2016-1-28
 * */
public class OrderStatusInfo {
	public String orderNo = null;
	public int objectStatus = 1;
	public String orderOrigin = "sit";
}
