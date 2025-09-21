# Spring Securityでエンドポイント毎に異なる認証を行う サンプルコード

このリポジトリのコードは [Spring Securityでエンドポイント毎に異なる認証を行う](https://knovus.site/?post_type=tech&p=1620) のサンプルコードです。

## Keycloak

サンプルコードでは認証基盤として [Keycloak](https://www.keycloak.org/) を使用しています。
以下のユーザーが登録された Keycloak を Docker Compose を使用して立ち上げることが出来ます。

|権限|ID|Password|
|---|---|---|
|管理者|admin|admin|
|一般|user1|password|
|一般|user2|password|
|一般|user3|password|