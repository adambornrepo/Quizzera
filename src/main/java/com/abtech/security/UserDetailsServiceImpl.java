package com.abtech.security;




public class UserDetailsServiceImpl  {
/*
    private UserInfoRepository userInfoRepository;

    @Autowired
    public UserDetailsServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User info not found with this username : " + username));

        return new org.springframework.security.core.userdetails.User(
                userInfo.getUsername(),
                userInfo.getPassword(),
                buildGrantedAuthority(userInfo.getUserRole())
        );
    }

    private static List<SimpleGrantedAuthority> buildGrantedAuthority(final Role role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }
*/

}
