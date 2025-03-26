This project, Digiton - Open Banking, is designed to facilitate and simulate open banking transactions, including initializing transactions, fetching current balances, and disbursing funds to users.

Prerequisites

Before running the application, ensure you have the following installed:
	•	Java Development Kit (JDK) 8 or higher: Required to run the application.
	•	Apache Maven: For managing project dependencies and building the application.

Setup and Execution

Follow these steps to set up and run the application:
	1.	Start the Payment Test Server:
	•	Begin by running the payment-test-server. This server simulates the payment processing environment.
	2.	Run Digiton Open Banking Application:
	•	Execute the digiton-openbanking application. This can typically be done using the provided shell script:

./digiton-openbanking.sh

	3.	Initialize Transactions:
	•	Use the sample.json file to post transaction data:

curl -X POST http://localhost:8090/inittransactions -H 'Content-Type: application/json' -d @sample.json

	•	This step submits transaction data for processing.

  4.	Fetch Current Balance:
	•	To retrieve the current balance, open the following URL in your browser:

http://localhost:8090/onlinestatement

	•	This will display the current balance information.

  5.	Disburse Funds to a Recipient User:
	•	To disburse funds to a specific user, access the following URL, replacing {recipient_user_id} with the actual user ID:

http://localhost:8090/trigger_recipient_user/{recipient_user_id}

	•	For example, to disburse funds to the user with ID 1:

http://localhost:8090/trigger_recipient_user/1



Data Flow and Storage
	•	Transaction Initialization:
	•	Upon posting data as described in Step 3, the application performs security validations and stores the information in the following tables:
	1.	disburse_request
	2.	recipient_user
	3.	user_bank
	•	Fetching Current Balance:
	•	Accessing the /onlinestatement endpoint retrieves the current balance by sending a JSON request to the payment test server.
