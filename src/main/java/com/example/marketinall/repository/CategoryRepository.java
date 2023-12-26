package com.example.marketinall.repository;

import com.example.marketinall.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // 부모 카테고리 아이디 오름차순 정렬하고, null 값 처리 후 자신 카테고리 기준으로 오름차순 정렬 조회
//    @Query("select c from Category c left join c.parent p order by p.id asc nulls first, c.id asc")
//    List<Category> findAllOrderByParentIdAscNullsFirstCategoryIdAsc();

    // 대분류 출력
    @Query(value = "select * from category where parent_id = 0", nativeQuery = true)
    List<Category> findAllOrderByParentIdZero();

    // 중분류 출력
    @Query(value = "select * from category where parent_id = :parent_id", nativeQuery = true)
    List<Category> findAllOrderByChild(@Param("parent_id") Long parentId);
}
