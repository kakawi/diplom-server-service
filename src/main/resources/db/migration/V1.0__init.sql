create table account
(
  id   bigint       not null
    constraint pk_account_id
      primary key,
  name varchar(100) not null
);

create table product
(
  id   bigint       not null
    constraint pk_product_id
      primary key,
  name varchar(100) not null
);

create table product_statistics
(
  id          bigint  not null
    constraint pk_product_statistics_id
      primary key,
  sell        bigint  not null,
  week_number integer not null,
  year        integer not null,
  product_id  bigint  not null
    constraint fk_product_statistics_product
      references product
);

create table campaign
(
  id         bigint not null
    constraint pk_campaign_id
      primary key,
  name       varchar(100),
  account_id bigint not null
    constraint fk_campaign_account
      references account,
  product_id bigint not null
    constraint "_1"
      unique
    constraint fk_campaign_product
      references product
);

create table campaign_statistic
(
  id          bigint  not null
    constraint pk_campaign_budget_history_id
      primary key,
  week_number integer not null,
  spends      bigint  not null,
  impressions bigint  not null,
  clicks      bigint  not null,
  campaign_id bigint  not null
    constraint fk_campaign_budget_history
      references campaign,
  year        integer not null,
  constraint "_0"
    unique (week_number, year, campaign_id)
);

create table ad_set
(
  id          bigint not null
    constraint pk_ad_set_id
      primary key,
  name        varchar(100),
  campaign_id bigint not null
    constraint fk_ad_set_campaign
      references campaign
);

create table ad_set_statistic
(
  id          bigint  not null
    constraint pk_ad_set_budget_history_id
      primary key,
  week_number integer not null,
  spends      bigint  not null,
  impressions bigint  not null,
  clicks      bigint  not null,
  ad_set_id   bigint  not null
    constraint fk_ad_set_budget_history
      references ad_set,
  year        integer not null
);

create table ad
(
  id        bigint
    constraint unq_ad_id
      unique,
  name      varchar(100),
  ad_set_id bigint not null
    constraint fk_ad_ad_set
      references ad_set
);

create table ad_statistic
(
  id          bigint  not null
    constraint pk_ad_spend_id
      primary key,
  week_number integer not null,
  spends      bigint  not null,
  impressions bigint  not null,
  clicks      bigint  not null,
  ad_id       bigint  not null
    constraint fk_ad_spend_ad
      references ad (id),
  year        integer not null
);
