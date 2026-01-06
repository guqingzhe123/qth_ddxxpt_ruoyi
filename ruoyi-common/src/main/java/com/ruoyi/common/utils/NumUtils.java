package com.ruoyi.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class NumUtils {
    /**
     * 均匀分配一个 BigDecimal 总数（取整，穿插均匀分配）
     * 核心思路：将 BigDecimal 放大为整数进行计算，避免浮点数精度问题
     *
     * @param total 总数（如 new BigDecimal("60.00")）
     * @param parts 份数（如 25）
     * @return 穿插分配后的结果列表，每个元素为 Long 类型
     */
    public static List<Long> splitAverageInterleaved(BigDecimal total, int parts) {
        List<Long> result = new ArrayList<>(parts);

        // 1. 输入合法性校验
        if (parts <= 0) {
            throw new IllegalArgumentException("份数必须大于 0");
        }
//        if (total == null || total.compareTo(BigDecimal.ZERO) < 0) {
//            throw new IllegalArgumentException("总数必须是一个非负的 BigDecimal");
//        }
        if (total.compareTo(BigDecimal.ZERO) == 0) {
            for (int i = 0; i < parts; i++) {
                result.add(0L);
            }
            return result;
        }

        // 2. 使用 divideAndRemainder 同时计算商和余数
        BigDecimal[] results = total.divideAndRemainder(BigDecimal.valueOf(parts));
        BigDecimal baseBig = results[0]; // 商，即基础每份值
        BigDecimal remainderBig = results[1]; // 余数

        // 3. 将 BigDecimal 转换为 Long
        long base = baseBig.longValue();
        int remainder = remainderBig.intValue();

        // --- 核心修改开始 ---
        // 如果没有余数，直接将 base 重复 parts 次即可
        if (remainder == 0) {
            for (int i = 0; i < parts; i++) {
                result.add(base);
            }
            return result;
        }
        // --- 核心修改结束 ---

        // 4. 只有当有余数时，才执行穿插分配逻辑
        int interval = parts / remainder; // 计算间隔
        int count = 0; // 已分配的余数计数

        for (int i = 0; i < parts; i++) {
            // 当已分配的余数数量小于总余数，并且当前索引是间隔的整数倍时，分配 base + 1
            if (count < remainder && i % interval == 0) {
                result.add(base + 1);
                count++;
            } else {
                result.add(base);
            }
        }

        return result;
    }
    /**
     * 计算 BigDecimal 类型参数除以 5 的结果，保留 2 位小数，返回 BigDecimal
     * @param monthPlan 被除数（BigDecimal 类型，如 subpo.getMonthPlan() 的返回值）
     * @return 保留 2 位小数的 BigDecimal 结果（非 null）
     * @throws IllegalArgumentException 若入参 monthPlan 为 null
     */
    public static BigDecimal divideBy5WithTwoDecimals(BigDecimal monthPlan , int day) {
        // 1. 入参非空校验（避免空指针异常）
        if (monthPlan == null) {
            throw new IllegalArgumentException("入参 monthPlan 不能为 null");
        }

        // 2. 定义除数 5（用 BigDecimal 表示，避免浮点精度问题）
        BigDecimal divisor = new BigDecimal(day);

        // 3. 除法计算：保留 2 位小数，四舍五入（商业常用规则）
        // 强制 setScale 确保结果一定是 2 位小数（极端场景兜底）
        return monthPlan.divide(
                divisor,
                2,
                RoundingMode.HALF_UP // 舍入模式：四舍五入（可按需调整）
        ).setScale(2, RoundingMode.HALF_UP);
    }
}
