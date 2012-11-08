package factory.global.network;

public interface NetworkManager{
		public void registerClientListener(NetworkBridge newBridge, int cID);
		public void closeNetworkBridge(int bridgeID);
}