

contract IllegalDecorate {

    constructor() payable public{}

    fallback() payable external{}

     function transferTokenWithPure(address payable toAddress, uint256 tokenValue) public pure{

        toAddress.transferToken(tokenValue, 0x6e6d62);

    }

}