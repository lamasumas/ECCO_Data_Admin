package mongoManager;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface CountryRepository extends MongoRepository<Country, String>
{
	
}
