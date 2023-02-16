package biz.buynow.bank.schedulejobs;

import biz.buynow.bank.model.MTBBeftnTransReq;
import biz.buynow.bank.model.MTBBeftnStatusReq;
import biz.buynow.bank.model.MTBOnlineStmtReq;
import biz.buynow.bank.model.MTBtoMTBTransReq;
import biz.buynow.bank.repository.MTBBeftnTransReqRepository;
import biz.buynow.bank.repository.MTBBeftnStatusReqRepository;
import biz.buynow.bank.repository.MTBOnlineStmtReqRepository;
import biz.buynow.bank.repository.MTBtoMTBFundTransferRequestRepository;

public class DisbursementSaveRequest {
    static public MTBtoMTBTransReq saveMTBToMTBFundTransferRequest(
            MTBtoMTBFundTransferRequestRepository mtbtoMTBFundTransferRequestRepository,
            MTBtoMTBTransReq mtbtoMTBFundTransferRequest) {
        return mtbtoMTBFundTransferRequestRepository.save(mtbtoMTBFundTransferRequest);
    }

    static public MTBBeftnTransReq saveBeftnInfoRequest(
            MTBBeftnTransReqRepository beftnInfoRequestRepository,
            MTBBeftnTransReq mtblBeftnInfoRequest) {
        return beftnInfoRequestRepository.save(mtblBeftnInfoRequest);
    }

    static public MTBBeftnStatusReq saveBeftnStatusRequest(
            MTBBeftnStatusReqRepository beftnStatusRequestRepository,
            MTBBeftnStatusReq mtblBeftnStatusRequest) {
        return beftnStatusRequestRepository.save(mtblBeftnStatusRequest);
    }

    static public MTBOnlineStmtReq saveOnlineStmtReq(
            MTBOnlineStmtReqRepository mtblOnlineStatusRequestRepository,
            MTBOnlineStmtReq mtblOnlineStatusRequest) {
        return mtblOnlineStatusRequestRepository.save(mtblOnlineStatusRequest);
    }
}
