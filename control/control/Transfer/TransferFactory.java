package control.Transfer;

import inf.Transfer.Transfer;

public class TransferFactory{
	private TransferFactory() {
	}
	
	private static class Holder {
        public static final TransferFactory Transfer = new TransferFactory();
    }
	
	public static TransferFactory getInstance() {
        return Holder.Transfer;
    }
	
	public Transfer getTransfer(String cate, String transferType) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return (Transfer) Class.forName(cate+transferType).newInstance();
	}
}
