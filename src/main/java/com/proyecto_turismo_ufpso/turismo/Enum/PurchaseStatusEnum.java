package com.proyecto_turismo_ufpso.turismo.Enum;

public enum PurchaseStatusEnum {

    PENDING("PENDING"),
    PAID("PAID"),
    CANCELED("CANCELED");

    private String codigo;




    PurchaseStatusEnum(String codigo) {
        this.codigo = codigo;
    }

    public static PurchaseStatusEnum getValueOf(String codigo) {
        for (PurchaseStatusEnum purchaseStatusEnum : PurchaseStatusEnum.values())
            if (purchaseStatusEnum.getCodigo().equals(codigo))
                return purchaseStatusEnum;

        throw new IllegalArgumentException();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
