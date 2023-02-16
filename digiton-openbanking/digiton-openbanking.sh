#!/bin/bash
for ((i=1;i<=100;i++))
do
echo "Loop No: $i"
disburse_request_id=$(curl -X POST http://localhost:8090/inittransactions -H 'Content-Type: application/json' -d @sample.json)
echo "Disburse Request Id: $disburse_request_id"
curl http://localhost:8090/trigger_disburse_request/$disburse_request_id
done
