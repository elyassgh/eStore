package elyassgh.estore.api.ServicesImpl;

import elyassgh.estore.api.Beans.ProductImage;
import elyassgh.estore.api.Beans.ProductObject;
import elyassgh.estore.api.Repositories.ProductImageRepository;
import elyassgh.estore.api.Services.ProductImageService;
import elyassgh.estore.api.Services.ProductObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    public ProductImageRepository repository;

    @Autowired
    public ProductObjectService productObjectService;

    @Override
    public int upload(MultipartFile uploadedImage, Long productObjectId) throws IOException {
        ProductObject productObject = productObjectService.findPOById(productObjectId).orElseThrow(()-> new RuntimeException("ProductObject Not Found"));
        ProductImage image =
                new ProductImage(uploadedImage.getOriginalFilename(),uploadedImage.getContentType(),
                compress(uploadedImage.getBytes()), productObject);
        try {
            repository.save(image);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ProductImage findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Error"));
    }

    @Override
    public ProductImage getImage(Long productObjectId, String name) {
        ProductObject productObject = productObjectService.findPOById(productObjectId).orElseThrow(()-> new RuntimeException("ProductObject Not Found !"));
        final ProductImage wantedImg = repository.findByProductObjectAndImageName(productObject, name);
        return new ProductImage(wantedImg.getImageName(), wantedImg.getImageType(),
                decompress(wantedImg.getImageBytesArray()), wantedImg.getProductObject() );
    }

    @Override
    public List<ProductImage> getImages(Long productObjectId) throws IOException {
        ProductObject productObject = productObjectService.findPOById(productObjectId).orElseThrow(()-> new RuntimeException("ProductObject Not Found !"));
        final List<ProductImage> imgs = repository.findProductImagesByProductObject(productObject);
        List<ProductImage> wantedImgs = new ArrayList<>();
        imgs.forEach( img -> {
            wantedImgs.add(new ProductImage(img.getImageName(), img.getImageType(),
                    decompress(img.getImageBytesArray()), img.getProductObject()));
        });
        return wantedImgs;
    }

    @Override
    public int delete(Long id) {
        try {
            ProductImage productImage = this.findById(id);
            repository.delete(productImage);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Image Bytes Compressor ( IMPORTANT ! --> Optimizing data storage )
    public static byte[] compress (byte[] data) {
        Deflater compressor = new Deflater();
        compressor.setInput(data);
        compressor.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!compressor.finished()) {
            int count = compressor.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    //Image Bytes Decompressor
    public static byte[] decompress (byte[] data) {
        Inflater decompressor = new Inflater();
        decompressor.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!decompressor.finished()) {
                int count = decompressor.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    // STOPSHIP: 30/09/2020

}
