package br.com.tirolezo.imeatapi.repositories;

import br.com.tirolezo.imeatapi.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends MongoRepository<User, String> {

    UserDetails findByLogin(String login);

}
