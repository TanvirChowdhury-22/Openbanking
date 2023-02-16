Digiton - Open Banking

Step 1: run payment-test-server

Step 2: run digiton-openbanking

Step 3: curl -X POST http://localhost:8090/inittransactions -H 'Content-Type: application/json' -d @sample.json

Step 4: to fetch current balance, hit this link on your browser: http://localhost:8090/onlinestatement

Step 5: to disburse money of an user, hit this link on your browser: http://localhost:8090/trigger_recipient_user/{recipient_user_id} , e.g. http://localhost:8090/trigger_recipient_user/1, for recipient_user_id = 1



Details:
from the json post mentioned in step 3, the data will be stored in the following tables after security validation
1. disburse_request, 
2. recipient_user,
3. user_bank

for fetching current balance the link mentioned in step 4 will be hit. 
By hitting this link, a json request will be sent to the server for online statement. 
In return, a corresponding response will come from server.
The response are stored in the following tables
1. res_json_dump
2. online_statement_res_mtb
3. transaction_list_mtb
current_balance can be fetched from table transaction_list_mtb,
by the data in the last_transaction_id column of table online_statement_res_mtb
which is the transaction_id of table transaction_list_mtb


