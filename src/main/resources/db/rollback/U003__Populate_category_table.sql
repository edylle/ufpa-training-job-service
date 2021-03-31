DELETE FROM categories WHERE category_id IN
(
'8af68b30-5a3d-4635-af9a-e126a3fa3970',
'a980eff5-18f5-4738-aa09-4351e355d0df',
'f790b65e-b65f-4b2a-b979-a624a9287fbe',
'e2c8a420-4f5f-41de-8884-7b6648fbb67f',
'2f3714f3-6c28-438e-8286-d757b8cc6e3d',
'ca845ff4-ed8d-4993-a9a5-4694fc6d1bf5',
'4756d097-333f-4dc1-8c78-6b9b9fdbfc69',
'cc225025-a2f9-41ae-917c-e8b6e82a4f15',
'cfbafcad-a7c2-4a2f-8f1b-31df7b3d157e',
'cc3a4a92-f885-474e-be50-16c33ad7d125',
'420fb0a1-35e0-4703-8633-df1ffdee1030',
'583937b3-d0d7-47dd-a306-69e9cb5c6bfe',
'8434d305-8082-40bf-a7db-f852383b1fdc'
);

DELETE FROM schema_version WHERE version = '003';