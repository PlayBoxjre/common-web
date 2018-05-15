package com.kong.support.socket.helper;

import java.net.Socket;

/**
 * 保存socket会话信息
 */
public class SocketSession {

    private SOCKET_STATUS socketStatus;

    private Socket socket;


    public static enum SOCKET_STATUS{
        SOCKET_DISCONNECT,SOCKET_CONNECTED,SOCKET_EXCEPTION_CLOSE,
        SOCKET_RECONNECTED,SOCKET_CLOSE
    }

    public SOCKET_STATUS getSocketStatus() {
        return socketStatus;
    }

    public void setSocketStatus(SOCKET_STATUS socketStatus) {
        this.socketStatus = socketStatus;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
