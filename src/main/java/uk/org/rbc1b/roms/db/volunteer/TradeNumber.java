/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.db.volunteer;

/**
 *
 * @author oliver.elder.esq
 */
public class TradeNumber {

    private Integer tradeNumberId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTradeNumberId() {
        return tradeNumberId;
    }

    public void setTradeNumberId(Integer tradeNumberId) {
        this.tradeNumberId = tradeNumberId;
    }

    @Override
    public String toString() {
        return "TradeNumber{" + "tradeNumberId=" + tradeNumberId + '}';
    }
}
