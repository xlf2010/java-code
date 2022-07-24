# java-code include spring cloud

account module include:

- create account
- recharge
- withdraw
- transaction
- batch transaction
- frozen
- unfrozen
- query account info
- query account flow
- delete account

trade:

1. connect third pay channel,like WeChat,alipay,paypal,amazon etc.
2. call account to sync the pay channel result include:

- create account
- recharge
- withdraw
- transaction
- frozen
- unfrozen

dependency:
consul(service discovery),mysql,redis
