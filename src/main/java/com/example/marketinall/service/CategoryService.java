package com.example.marketinall.service;

import com.example.marketinall.domain.dto.CategoryDto;
import com.example.marketinall.domain.entity.Category;
import com.example.marketinall.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryDto createCategoryRoot() {
        Map<Long, List<CategoryDto>> groupingByParent = categoryRepository.findAll()
                .stream()
                .map(ce -> new CategoryDto(ce.getId(), ce.getName(), ce.getParentId()))
                .collect(groupingBy(cd -> cd.getParentId()));

        CategoryDto rootCategoryDto = new CategoryDto(0l, "ROOT", null);
        addSubCategories(rootCategoryDto, groupingByParent);

        return rootCategoryDto;
    }


    /**
     * 하위 카테고리 추가
     * 1) 부모 카테고리키로 하위 카테고리 찾는다
     * 2) 하위 카테고리리 세팅한다.
     * 3) 재귀적으로 하위카테고리들에 대해 수행
     * */
    private void addSubCategories(CategoryDto parent, Map<Long, List<CategoryDto>> groupingByParentId) {
        // 1. parent의 키로 subCategories를 찾는다.
        List<CategoryDto> subCategories = groupingByParentId.get(parent.getId());

        // 종료 조건
        if (subCategories == null)
            return;

        // 2. sub categories 셋팅
        parent.setSubCategories(subCategories);

        // 3. 재귀적으로 subcategories들에 대해서도 수행
        subCategories.stream()
                .forEach(s -> {
                    addSubCategories(s, groupingByParentId);
                });
    }

    public List<Category> findAllCategory(Long parentId) {
        if (parentId == null) {
            return categoryRepository.findAllOrderByParentIdZero();
        }
        return categoryRepository.findAllOrderByChild(parentId);
    }


}
