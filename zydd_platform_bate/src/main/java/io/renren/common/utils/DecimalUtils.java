package io.renren.common.utils;

import io.renren.common.exception.RRException;

import java.math.BigDecimal;

public class DecimalUtils {
    /**
     * 默认折扣
     */
    public static final BigDecimal DEFAULT_DISCOUNT=new BigDecimal("100");

    /**
     * 计算码洋
     * @param price 价格
     * @param num 数量
     * @return
     */
    public static BigDecimal mayang(BigDecimal price,Integer num)
    {
        if(price==null||new BigDecimal("0.00").compareTo(price)>=0)
        {
             throw new RRException("价格为0！");
        }
        return price.multiply(BigDecimal.valueOf(num));
    }
    public static BigDecimal mayang(Double price,Integer num)
    {
        if(price==null||new BigDecimal("0.00").compareTo(BigDecimal.valueOf(price))>=0)
        {
            throw new RRException("价格为0！");
        }
        return BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(num));
    }
    /**
     *  计算实洋
     * @param price 价格
     * @param num 数量
     * @param discount 折扣
     * @return
     */
    public static BigDecimal shiyang(BigDecimal mayang,BigDecimal discount)
    {
       /* if(mayang==null||new BigDecimal("0.00").compareTo(mayang)>=0)
        {
            throw new RRException("码洋为0！");
        }*/
      /*  if(DEFAULT_DISCOUNT.compareTo(discount)<0)
        {
            throw new RRException("折扣不能大于100");
        }*/
        BigDecimal  b =discount.divide(DEFAULT_DISCOUNT,4,BigDecimal.ROUND_DOWN);
        return mayang.multiply(b);
    }
    public static BigDecimal shiyang(BigDecimal price,Integer num,BigDecimal discount)
    {
        if(price==null||new BigDecimal("0.00").compareTo(price)>=0)
        {
            throw new RRException("价格为0！");
        }
        /*if(DEFAULT_DISCOUNT.compareTo(discount)<0)
        {
            throw new RRException("折扣不能大于100");
        }*/
        BigDecimal  b =discount.divide(DEFAULT_DISCOUNT,4,BigDecimal.ROUND_DOWN);
        return price.multiply(BigDecimal.valueOf(num)).multiply(b);
    }
    public static BigDecimal shiyang(Double price,Integer num,BigDecimal discount)
    {
        if(price==null||new BigDecimal("0.00").compareTo(BigDecimal.valueOf(price))>=0)
        {
            throw new RRException("价格为0！");
        }
      /*  if(DEFAULT_DISCOUNT.compareTo(discount)>0)
        {
            throw new RRException("折扣不能大于100");
        }*/
        BigDecimal  b =discount.divide(DEFAULT_DISCOUNT,4,BigDecimal.ROUND_DOWN);
        return BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(num)).multiply(b);
    }
}
