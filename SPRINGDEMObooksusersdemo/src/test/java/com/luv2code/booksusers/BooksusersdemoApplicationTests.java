package com.luv2code.booksusers;

import com.luv2code.booksusers.dao.BooksDAO;
import com.luv2code.booksusers.entity.Books;
import com.luv2code.booksusers.service.BooksService;
import com.luv2code.booksusers.service.BooksServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class BooksusersdemoApplicationTests {


	@InjectMocks
			@Autowired
	BooksServiceImpl service;

	@Mock
	BooksDAO dao;

	@BeforeTestExecution
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void findAllTest(){
		List<Books> list = new ArrayList<>();
		Books p1 = new Books(1,"Spinz powder",99,null);
		Books p2 = new Books(2,"Boroplus powder",59,null);
		Books p3 = new Books(3,"ponds powder",79,null);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		when(dao.findAll()).thenReturn(list);

		//test
		List<Books> productList = service.findAll(); //service

		assertEquals(5, productList.size());
		//verify(dao, times(1)).findAll();
	}


	@Test
	void findByIdTest()
	{
		when(dao.findById(1)).thenReturn((new Books(1, "Santoor powder", 89, null)));
		Books product = service.findById(10);

		assertEquals("Santoor powder", product.getTitle());
		assertEquals(89, product.getPrice());
//		assertEquals(1, product.getQuantity());
	}

	@Test
	void saveTest()
	{
		Books product = new Books(1,"Santoor powder",89,null);

		service.save(product);

		verify(dao, times(1)).save(product);
	}




}
