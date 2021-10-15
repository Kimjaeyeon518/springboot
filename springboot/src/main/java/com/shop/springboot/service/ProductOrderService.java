//package com.shop.springboot.service;
//
//import com.shop.springboot.entity.Cart;
//import com.shop.springboot.entity.Product;
//import com.shop.springboot.entity.ProductOrder;
//import com.shop.springboot.entity.User;
//import com.shop.springboot.exception.NotExistOrderException;
//import com.shop.springboot.exception.NotExistProductException;
//import com.shop.springboot.exception.NotExistUserException;
//import com.shop.springboot.repository.CartRepository;
//import com.shop.springboot.repository.ProductOrderRepository;
//import com.shop.springboot.repository.ProductRepository;
//import com.shop.springboot.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Transactional
//@RequiredArgsConstructor
//@Service
//public class ProductOrderService {
//
//    private final UserRepository userRepository;
//    private final CartRepository cartRepository;
//    private final ProductOrderRepository productOrderRepository;
//    private final ProductRepository productRepository;
////
////    // 주문 조회
////    public ProductOrderResponseDto findOne(Long orderId) {
////
////        Optional<ProductOrder> orderOpt = productOrderRepository.findById(orderId);
////
////        if (!orderOpt.isPresent())
////            throw new NotExistOrderException("존재하지 않는 주문입니다.");
////
////        return orderOpt.get().toResponseDto();
////    }
//
//    // 주문 조회    ->  ProductOrder Entity 테스트용
//    public ProductOrder findOneProductOrder(Long orderId) {
//
//        Optional<ProductOrder> orderOpt = productOrderRepository.findById(orderId);
//
//        if (!orderOpt.isPresent())
//            throw new NotExistOrderException("존재하지 않는 주문입니다.");
//
//        return orderOpt.get();
//    }
//
//    public Long save(Long userId) {
//
//        int totalPrice = 0;
//
//        User user = userRepository.findById(userId).orElseThrow(()
//                -> new NotExistUserException("존재하지 않는 유저입니다."));
//
//        List<Cart> carts = cartRepository.findAllByUserIdOrderByCreatedTimeDesc(user.getId());
//
//        if(carts.isEmpty())
//            throw new NotExistProductException("장바구니에 상품이 없습니다.");
//
//        ProductOrder productOrder = new ProductOrder();
//        productOrder.setUser(user);
//
//        for (Cart cart : carts) {
//            cart.setProductOrder(productOrder);
//            cartRepository.save(cart);
//
//            totalPrice += cart.getTotalPrice();
//
//            Product product = cart.getProduct();
//            product.setTotalCount((product.getTotalCount() - cart.getProductCount()));
//            product.setBuyCount(cart.getProductCount());
//
//            productRepository.save(product);
//        }
//
//        productOrder.setTotalPrice(totalPrice);
//
//        return productOrderRepository.save(productOrder).getId();
//
//    }
//
//    // 주문 리스트 조회
//    public List<ProductOrder> findProductOrdersList(Long userId) {
//        return productOrderRepository.findAllByUserIdOrderByCreatedTimeDesc(userId).stream()
//                .map(ProductOrder::new)
//                .collect(Collectors.toList());
//    }
//
//
//    // 주문 삭제
//    public void delete(Long productOrderId) {
//        productOrderRepository.deleteById(productOrderId);
//    }
//
//}
