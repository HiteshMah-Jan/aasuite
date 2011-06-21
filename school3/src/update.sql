CREATE VIEW `school`.`viewSOA` AS
  select schoolYear, paidBy, recordId, paymentFor, amount, max(discount) disc, sum(amountPaid) paid, max(balance) bal
from payment
group by schoolYear, paidBy, recordId, paymentFor, amount;

