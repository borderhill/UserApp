package com.sample.controller;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.sample.data.User;
import com.sample.data.User_;

public class UserSpecifications {

	public static Specification<User> byKeyword(String keyword, String email, String userName){
		return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

			List<Predicate> predicates = new ArrayList<>();

			System.out.println("keywork" + keyword);
			if (StringUtils.hasText(keyword)) {
				predicates.add(
						cb.or(
							cb.like(root.get(User_.email), "%" + keyword + "%"),
							cb.like(root.get(User_.userName), "%" + keyword + "%")
						));
			}

			if (StringUtils.hasText(email)) {
				predicates.add(cb.equal(root.get(User_.email), email));
			}
			if (StringUtils.hasText(userName)) {
				predicates.add(cb.equal(root.get(User_.userName), userName));
			}
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
    }
    

	
}
