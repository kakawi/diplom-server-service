create table campaign_statistic_day
(
    id             bigint not null
        constraint pk_campaign_statistic_day_id
            primary key,
    date_statistic date   not null,
    spends         bigint not null,
    impressions    bigint not null,
    clicks         bigint not null,
    campaign_id    bigint not null
        constraint fk_campaign_statistic_day
            references campaign
);
