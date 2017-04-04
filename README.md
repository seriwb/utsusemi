[![CircleCI](https://circleci.com/gh/seriwb/utsusemi.svg?style=shield)](https://circleci.com/gh/seriwb/utsusemi)
[![Coverage Status](https://coveralls.io/repos/github/seriwb/utsusemi/badge.svg?branch=master)](https://coveralls.io/github/seriwb/utsusemi?branch=master)

# 空蝉（utsusemi）

## 要求
- java 8+


## JDBC
以下のデータベースに関してはプログラム内部でJDBCドライバを持っています。

- MySQL
- PostgreSQL
- Oracle
- SQLite


# TODO
- [ ] コマンドライン引数の取得にライブラリを導入（Commons CLIかargs4j）
- [ ] 外部ファイルからのパラメータ読み込みを可能にする
  - [ ] プロパティファイルの場所指定をコマンドラインからできるようにする（flyway参考）
- [ ] テンプレートでの多言語対応