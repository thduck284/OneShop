package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dao.ProductDAO;
import models.Product;
import models.ProductFavorite;
import models.ProductReview;

public class ProductDAOImpl implements ProductDAO{
	
	@Override
	public void addProduct(Product product)  {
	    String sql = "INSERT INTO product (productId, productName, description, price, quantity, categoryId, shopId, image, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    String id = "SP" + UUID.randomUUID().toString().replace("-", "").substring(0, 7);
	    
	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) { 

	        statement.setString(1, id);
	        statement.setString(2, product.getProductName());
	        statement.setString(3, product.getDescription());
	        statement.setInt(4, product.getPrice()); 
	        statement.setInt(5, product.getQuantity());
	        statement.setString(6, product.getCategoryId());
	        statement.setString(7, product.getShopId());

	        if (product.getImage() != null) {
	            statement.setBytes(8, product.getImage());
	        } else {
	            statement.setNull(8, java.sql.Types.BLOB);
	        }
	        
	        statement.setDate(9, new java.sql.Date(product.getCreatedDate().getTime()));

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void deleteProduct(String productId) {
	    String sql = "DELETE FROM product WHERE productId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, productId); 
	        statement.executeUpdate(); 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void updateProduct(Product product) {
	    String sql = "UPDATE product SET productName = ?, description = ?, price = ?, quantity = ?, categoryId = ?, shopId = ?, image = ?, createdDate = ? WHERE productId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, product.getProductName());
	        statement.setString(2, product.getDescription());
	        statement.setInt(3, product.getPrice());
	        statement.setInt(4, product.getQuantity());
	        statement.setString(5, product.getCategoryId());
	        statement.setString(6, product.getShopId());

	        if (product.getImage() != null) {
	            statement.setBytes(7, product.getImage());
	        } else {
	            statement.setNull(7, java.sql.Types.BLOB);
	        }

	        statement.setDate(8, new java.sql.Date(product.getCreatedDate().getTime()));
	        statement.setString(9, product.getProductId());

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
            	
            	byte[] image = resultSet.getBytes("image");
            	
                Product product = new Product(
                    resultSet.getString("productId"),
                    resultSet.getString("categoryId"),
                    resultSet.getString("shopId"),
                    resultSet.getString("productName"),
                    resultSet.getString("description"),
                    resultSet.getInt("price"),
                    resultSet.getInt("quantity"),
                    image,
                    resultSet.getDate("createdDate")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
	
	@Override
	public int countProducts() {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM product";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        if (resultSet.next()) {
	            count = resultSet.getInt(1); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return count;
	}
	
	@Override
	public byte[] getImageById(String productId) {
	    String sql = "SELECT image FROM product WHERE productId = ?";
	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, productId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            
	            return resultSet.getBytes("image");
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return null;  
	}
	
	@Override
	public Product getProductById(String productId) {
	    String sql = "SELECT * FROM product WHERE productId = ?";
	    Product product = null;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, productId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            product = new Product(
            		resultSet.getString("productId"),
                    resultSet.getString("categoryId"),
                    resultSet.getString("shopId"),
                    resultSet.getString("productName"),
                    resultSet.getString("description"),
                    resultSet.getInt("price"),
                    resultSet.getInt("quantity"),
	                resultSet.getBytes("image"),  
	                resultSet.getDate("createdDate")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return product;
	}
	
	@Override
	public List<Product> getAllProductsByCategoryId(String categoryId) {
		// TODO Auto-generated method stub
		List<Product> productList = new ArrayList<>();
	    String sql = "SELECT * FROM product WHERE categoryId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, categoryId);  
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Product product = new Product(
	                resultSet.getString("productId"),
	                resultSet.getString("categoryId"),
	                resultSet.getString("shopId"),
	                resultSet.getString("productName"),
	                resultSet.getString("description"),
	                resultSet.getInt("price"),
	                resultSet.getInt("quantity"),
	                resultSet.getBytes("image"),
	                resultSet.getDate("createdDate")
	            );
	            productList.add(product); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return productList;
	}

	@Override
	public List<Product> getAllProductsByShopId(String shopId) {
		// TODO Auto-generated method stub
		List<Product> productList = new ArrayList<>();
	    String sql = "SELECT * FROM product WHERE shopId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, shopId);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Product product = new Product(
	                resultSet.getString("productId"),
	                resultSet.getString("categoryId"),
	                resultSet.getString("shopId"),
	                resultSet.getString("productName"),
	                resultSet.getString("description"),
	                resultSet.getInt("price"),
	                resultSet.getInt("quantity"),
	                resultSet.getBytes("image"),
	                resultSet.getDate("createdDate")
	            );
	            productList.add(product);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return productList;
	}

	@Override
	public List<Product> getAllProductsByUserId(String userId) {
		// TODO Auto-generated method stub
		List<Product> productList = new ArrayList<>();
	    String sql = "SELECT p.* FROM product p JOIN shop s ON p.shopId = s.shopId WHERE s.userId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, userId);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Product product = new Product(
	                resultSet.getString("productId"),
	                resultSet.getString("categoryId"),
	                resultSet.getString("shopId"),
	                resultSet.getString("productName"),
	                resultSet.getString("description"),
	                resultSet.getInt("price"),
	                resultSet.getInt("quantity"),
	                resultSet.getBytes("image"),
	                resultSet.getDate("createdDate")
	            );
	            productList.add(product);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return productList;
	}

	@Override
	public List<Product> searchProductsByKeyword(String keyword) {
		// TODO Auto-generated method stub
		List<Product> productList = new ArrayList<>();
	    String sql = """
	        SELECT p.*, c.categoryName
	        FROM product p 
	        JOIN category c ON p.categoryId = c.categoryId 
	        WHERE p.productName LIKE ? OR c.categoryName LIKE ?
	    """;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        String searchKeyword = "%" + keyword + "%";
	        statement.setString(1, searchKeyword);
	        statement.setString(2, searchKeyword);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                Product product = new Product(
	                    resultSet.getString("productId"),
	                    resultSet.getString("categoryId"),
	                    resultSet.getString("shopId"),
	                    resultSet.getString("productName"),
	                    resultSet.getString("description"),
	                    resultSet.getInt("price"),
	                    resultSet.getInt("quantity"),
	                    resultSet.getBytes("image"),
	                    resultSet.getDate("createdDate")
	                );
	                productList.add(product);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return productList;
	}

	@Override
	public List<Product> getNewProducts(int page, int pageSize) {
		// TODO Auto-generated method stub
		List<Product> productList = new ArrayList<>();
		String sql = "SELECT * FROM product ORDER BY createdDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

		try (Connection connection = ConnectDB.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)){
			int offset = (page - 1) * pageSize; 
			statement.setInt(1, offset);
			statement.setInt(2, pageSize);
			 try(ResultSet resultSet = statement.executeQuery()) {
				 while (resultSet.next()) {

					 byte[] image = resultSet.getBytes("image");

					 Product product = new Product(
							 resultSet.getString("productId"),
							 resultSet.getString("categoryId"),
							 resultSet.getString("shopId"),
							 resultSet.getString("productName"),
							 resultSet.getString("description"),
							 resultSet.getInt("price"),
							 resultSet.getInt("quantity"),
							 image,
							 resultSet.getDate("createdDate")
					 );
					 productList.add(product);
			 }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public List<Product> getBestSellingProducts() {
		// TODO Auto-generated method stub
		return List.of();
	}

	@Override
	public List<Product> getTopRatedProducts() {
		// TODO Auto-generated method stub
		return List.of();
	}

	@Override
	public List<ProductFavorite> getProductsByCriteria(String criteria, int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductFavorite> getProductFavorites() {
		// TODO Auto-generated method stub
		List<ProductFavorite> productFavoriteList = new ArrayList<>();
		String sql = "SELECT TOP 20 " +
				"p.productId, " +
				"p.productName, " +
				"p.price, " +
				"p.image, " +
				"p.shopId, " +
				"COUNT(w.productId) AS favoriteCount " +
				"FROM wishList w " +
				"JOIN product p ON w.productId = p.productId " +
				"GROUP BY p.productId, p.productName, p.price, p.image, p.shopId " +
				"ORDER BY favoriteCount DESC";

		try (Connection connection = ConnectDB.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {

				byte[] image = resultSet.getBytes("image");

				ProductFavorite productFavorite = new ProductFavorite(
						resultSet.getInt("favoriteCount"),
						image,
						resultSet.getInt("price"),
						resultSet.getString("productId"),
						resultSet.getString("productName"),
						resultSet.getString("shopId")

				);
				productFavoriteList.add(productFavorite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productFavoriteList;
	}

	@Override
	public List<ProductFavorite> getPagedFavoriteProducts(int page, int pageSize) {
		// TODO Auto-generated method stub
		List<ProductFavorite> productFavorites = new ArrayList<>();
		String sql = "SELECT " +
				"p.productId, p.productName, p.price, p.image, p.shopId, COUNT(w.productId) AS favoriteCount " +
				"FROM wishList w " +
				"JOIN product p ON w.productId = p.productId " +
				"GROUP BY p.productId, p.productName, p.price, p.image, p.shopId " +
				"ORDER BY p.productId ASC " +
				"OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

		try (Connection connection = ConnectDB.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			int offset = (page - 1) * pageSize;
			statement.setInt(1, offset);
			statement.setInt(2, pageSize);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					byte[] image = resultSet.getBytes("image");

					ProductFavorite productFavorite = new ProductFavorite(
							resultSet.getInt("favoriteCount"),
							image,
							resultSet.getInt("price"),
							resultSet.getString("productId"),
							resultSet.getString("productName"),
							resultSet.getString("shopId")

					);
					productFavorites.add(productFavorite);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productFavorites;
	}

	@Override
	public int countFavoriteProducts() {
		// TODO Auto-generated method stub
		String sql = """
		        SELECT COUNT(*) AS totalRecords
		        FROM (
		            SELECT p.[productId]
		            FROM [OneShop].[dbo].[wishList] w
		            JOIN [OneShop].[dbo].[product] p ON w.[productId] = p.[productId]
		            GROUP BY p.[productId], p.[productName], p.[price], p.[image], p.[shopId]
		        ) AS groupedProducts
		    """;

				try (Connection connection = ConnectDB.getConnection();
					 PreparedStatement preparedStatement = connection.prepareStatement(sql);
					 ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						return resultSet.getInt("totalRecords");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0;
	}

	@Override
	public int countNewProducts() {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "SELECT COUNT(*) " +
				"FROM ( " +
				"    SELECT TOP 20 * " +
				"    FROM product " +
				"    ORDER BY createdDate DESC " +
				") AS latestProducts";

		try (Connection connection = ConnectDB.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				count = resultSet.getInt(1); 
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public List<ProductReview> getProductsManyRated(int page, int pageSize) {
		// TODO Auto-generated method stub
		List<ProductReview> productReviews = new ArrayList<>();
		String sql = """
				SELECT 
					p.productid, 
					p.productName, 
					p.price, 
					p.image, 
					p.shopId, 
					COUNT(r.productid) AS review_count 
				FROM 
					review r 
				JOIN 
					product p ON r.productid = p.productid 
				GROUP BY 
					p.productid, p.productName, p.price, p.image, p.shopId 
				ORDER BY 
					review_count DESC
				OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;""";

		try (Connection connection = ConnectDB.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			int offset = (page - 1) * pageSize; // Tính toán OFFSET
			statement.setInt(1, offset);
			statement.setInt(2, pageSize);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					byte[] image = resultSet.getBytes("image");

					ProductReview productReview = new ProductReview(
							image,
							resultSet.getInt("price"),
							resultSet.getString("productid"),
							resultSet.getInt("review_count"),
							resultSet.getString("productName"),
							resultSet.getString("shopId")

					);
					productReviews.add(productReview);
				}
			}

		}
			catch (SQLException e) {
				e.printStackTrace();
			}
		return productReviews;
	}

	@Override
	public int countProductsManyRated() {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "SELECT COUNT(DISTINCT productid) AS total_count FROM review;";

		try (Connection connection = ConnectDB.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				count = resultSet.getInt(1); 
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public List<Product> getProductManyBuy(int page, int pageSize) {
		// TODO Auto-generated method stub
		return List.of();
	}

	@Override
	public int countProductsManyBuy() {
		// TODO Auto-generated method stub
		return 0;
	}
}
