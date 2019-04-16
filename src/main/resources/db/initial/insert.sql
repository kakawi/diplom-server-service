INSERT INTO account (id, name) VALUES (1, 'test_acc');

INSERT INTO product (id, name) VALUES (1, 'Product1');
INSERT INTO product (id, name) VALUES (2, 'Product2');
INSERT INTO product (id, name) VALUES (3, 'Product3');

INSERT INTO product_statistics (id, sell, week_number, year, product_id) VALUES (1, 10, 1, 2019, 1);
INSERT INTO product_statistics (id, sell, week_number, year, product_id) VALUES (2, 20, 2, 2019, 1);
INSERT INTO product_statistics (id, sell, week_number, year, product_id) VALUES (3, 30, 3, 2019, 1);
INSERT INTO product_statistics (id, sell, week_number, year, product_id) VALUES (4, 25, 4, 2019, 1);
INSERT INTO product_statistics (id, sell, week_number, year, product_id) VALUES (5, 27, 5, 2019, 1);
INSERT INTO product_statistics (id, sell, week_number, year, product_id) VALUES (6, 40, 6, 2019, 1);
INSERT INTO product_statistics (id, sell, week_number, year, product_id) VALUES (8, 33, 8, 2019, 1);
INSERT INTO product_statistics (id, sell, week_number, year, product_id) VALUES (9, 48, 9, 2019, 1);
INSERT INTO product_statistics (id, sell, week_number, year, product_id) VALUES (7, 55, 7, 2019, 1);

INSERT INTO campaign (id, name, account_id, product_id) VALUES (1, 'Product1_Campaign', 1, 1);
INSERT INTO campaign (id, name, account_id, product_id) VALUES (2, 'Product2_Campaign', 1, 2);
INSERT INTO campaign (id, name, account_id, product_id) VALUES (3, 'Product3_Campaign', 1, 3);

INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (20, 1, 10, 10, 10, 2, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (21, 2, 20, 20, 20, 2, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (22, 3, 30, 30, 30, 2, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (23, 4, 40, 40, 40, 2, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (24, 5, 50, 50, 50, 2, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (25, 6, 60, 55, 55, 2, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (30, 1, 10, 10, 10, 3, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (31, 2, 20, 20, 20, 3, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (32, 3, 10, 10, 10, 3, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (1, 1, 10, 100, 10, 1, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (9, 8, 80, 680, 110, 1, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (4, 3, 30, 290, 40, 1, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (7, 6, 60, 512, 95, 1, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (6, 5, 50, 458, 84, 1, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (10, 9, 90, 720, 115, 1, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (5, 4, 40, 375, 55, 1, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (3, 2, 20, 150, 20, 1, 2019);
INSERT INTO campaign_statistic (id, week_number, spends, impressions, clicks, campaign_id, year) VALUES (8, 7, 20, 160, 20, 1, 2019);

INSERT INTO ad_set (id, name, campaign_id) VALUES (1, 'adSet_1_1', 1);
INSERT INTO ad_set (id, name, campaign_id) VALUES (2, 'adSet_1_2', 1);
INSERT INTO ad_set (id, name, campaign_id) VALUES (3, 'adSet_2_1', 2);
INSERT INTO ad_set (id, name, campaign_id) VALUES (4, 'adSet_2_2', 2);
INSERT INTO ad_set (id, name, campaign_id) VALUES (5, 'adSet_3_1', 3);
INSERT INTO ad_set (id, name, campaign_id) VALUES (6, 'adSet_3_2', 3);

INSERT INTO ad_set_statistic (id, week_number, spends, impressions, clicks, ad_set_id, year) VALUES (1, 1, 10, 100, 200, 1, 2019);
