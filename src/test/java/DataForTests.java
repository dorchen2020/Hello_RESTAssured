import org.testng.annotations.DataProvider;

public class DataForTests {

	@DataProvider(name="PostData")
	public Object[][] dataForPost() {
		return new Object [][] { 	   
			{"Alex", "Ben Ari",25 ,2}, 
			{"Avi", "Shimon",30 ,1}    
		};							   
	}
	
	@DataProvider(name="PatchData")
	public Object[][] dataForPatch() {
		return new Object [][] { 	   
			{"Moshe", 26}     
		};							   
	}
	
	@DataProvider(name = "PutData")
	public Object[][] dataForPut() {
		return new Object [][] { 	   
			{4, "Itay", "Cohen", 16}, // update user 4, subjectId delete while update.
			{5, "Avi", "Cohen" ,42} // update user 5, subjectId delete while update.
		};							   
	}
	
	@DataProvider(name="DeleteData")
	public Object[] dataForDelete() {
		return new Object [] {
			4,5
		};
	}
}
