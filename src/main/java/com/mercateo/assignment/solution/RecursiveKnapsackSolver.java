//package com.mercateo.assignment.solution;
//
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Component
//public class RecursiveSolution implements KnapsackSolver {
//
//    public BigDecimal solve(List<BigDecimal> w, List<BigDecimal> v, Integer n, BigDecimal W) {
//        if (n <= 0) {
//            return new BigDecimal(0);
//        } else if (w.get(n - 1).compareTo(W) > 0) {
//            return solve(w, v, n - 1, W);
//        } else {
//            return
//                    solve(w, v, n - 1, W).max(v.get(n - 1).add(solve(w, v, n - 1, W.subtract(w.get(n - 1)))));
//        }
//
//    }
//
//}
